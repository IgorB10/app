package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.mapper

import com.igor.bykov.skyscannerapp.data.flight.model.Carriers
import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.flight.model.Leg
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightDetailsViewModel
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat


object FlightViewModelMapper {

  private val parser = ISODateTimeFormat.dateTimeParser()
  private val formatter = DateTimeFormat.forPattern("HH:mm")

  fun map(flightResponse: FlightResponse) : List<FlightViewModel> {

    val mapLegs = flightResponse.legs.map { it.id to it }.toMap()
    val mapSegments = flightResponse.segments.map { it.id to it }.toMap()
    val mapCarriers = flightResponse.carriers.map { it.id to it }.toMap()


    val results = mutableListOf<FlightViewModel>()

    for (itinerary in flightResponse.itineraries) {
      val firstFlight = mapFlightDetails(itinerary.inboundLegId, mapLegs, mapCarriers)
      val secondFlight = mapFlightDetails(itinerary.outboundLegId, mapLegs, mapCarriers)
      val model = FlightViewModel(itinerary.inboundLegId + itinerary.outboundLegId,
              firstFlight, secondFlight, 10, "${flightResponse.currencies[0].symbol}${itinerary.price[0].price}", "Wizz")
      results.add(model)
    }

    return results
  }

  private fun mapFlightDetails(id: String, mapLegs: Map<String, Leg>, mapCarriers: Map<Long, Carriers>) : FlightDetailsViewModel? {
    val leg = mapLegs[id]
    leg?.let {
      val logoUrl =  mapCarriers[leg.carriers[0]]?.ImageUrl
      val departure = formatter.print(parser.parseDateTime(leg.departure))
      val arrival = formatter.print(parser.parseDateTime(leg.arrival))
      return FlightDetailsViewModel(time = "$departure - $arrival", stops = "direct", carrierLogoUrl = logoUrl)
    }

  }
}