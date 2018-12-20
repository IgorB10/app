package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.mapper

import com.igor.bykov.skyscannerapp.data.flight.model.Carriers
import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.flight.model.Leg
import com.igor.bykov.skyscannerapp.data.flight.model.Place
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightDetailsViewModel
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatterBuilder
import org.joda.time.format.ISODateTimeFormat


object FlightViewModelMapper {

  private val parser = ISODateTimeFormat.dateTimeParser()
  private val formatter = DateTimeFormat.forPattern("HH:mm")

  private val monthAndYear = DateTimeFormatterBuilder()
          .appendHourOfDay(1)
          .appendLiteral('h')
          .appendLiteral(' ')
          .appendMinuteOfDay(1)
          .appendLiteral('m')
          .toFormatter()

  fun map(flightResponse: FlightResponse): List<FlightViewModel> {

    val mapLegs = flightResponse.legs.map { it.id to it }.toMap()
    val mapAgents = flightResponse.agents.map { it.id to it }.toMap()
    val mapCarriers = flightResponse.carriers.map { it.id to it }.toMap()
    val mapPlaces = flightResponse.places.map { it.id to it }.toMap()


    val results = mutableListOf<FlightViewModel>()

    for (itinerary in flightResponse.itineraries) {
      val firstFlight = mapFlightDetails(itinerary.inboundLegId, mapLegs, mapCarriers, mapPlaces)
      val secondFlight = mapFlightDetails(itinerary.outboundLegId, mapLegs, mapCarriers, mapPlaces)
      val model = FlightViewModel(
              id = itinerary.inboundLegId + itinerary.outboundLegId,
              firstFlight = firstFlight,
              secondFlight = secondFlight,
              rating = 10,
              price = "${flightResponse.currencies[0].symbol}${itinerary.price[0].price}",
              url = itinerary.price[0].link,
              operator = mapAgents[itinerary.price[0].agents[0]]?.name)
      results.add(model)
    }

    return results
  }

  private fun mapFlightDetails(id: String, mapLegs: Map<String, Leg>, mapCarriers: Map<Long, Carriers>, mapPlace: Map<Long, Place>): FlightDetailsViewModel? {
    val leg = mapLegs[id]
    leg?.let {
      val originStation = mapPlace[it.originStation]
      val destinationStation = mapPlace[it.destinationStation]

      val carrier = mapCarriers[leg.carriers[0]]
      val logoUrl = carrier?.ImageUrl
      val departure = formatter.print(parser.parseDateTime(leg.departure))
      val arrival = formatter.print(parser.parseDateTime(leg.arrival))
      return FlightDetailsViewModel(
              time = "$departure - $arrival",
              stops = "direct",
              carrierName = "${originStation?.code} - ${destinationStation?.code}, ${carrier?.name}",
              carrierLogoUrl = logoUrl,
              duration = monthAndYear.print(DateTime(it.duration)
              )
      )
    }

  }
}