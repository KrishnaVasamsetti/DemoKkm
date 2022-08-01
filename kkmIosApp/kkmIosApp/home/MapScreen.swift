//
//  MapScreen.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 28/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import MapKit

struct MapScreen: View {
    @State private var region = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 51.507222, longitude: -0.1275), span: MKCoordinateSpan(latitudeDelta: 0.5, longitudeDelta: 0.5))

    let annotations = [
            City(name: "Paris", coordinate: CLLocationCoordinate2D(latitude: 48.8567, longitude: 2.3508)),
           City(name: "London", coordinate: CLLocationCoordinate2D(latitude: 51.507222, longitude: -0.1275)),
           
           City(name: "Rome", coordinate: CLLocationCoordinate2D(latitude: 41.9, longitude: 12.5)),
           City(name: "Washington DC", coordinate: CLLocationCoordinate2D(latitude: 38.895111, longitude: -77.036667))
       ]
    
    var body: some View {
        Map(coordinateRegion: $region, annotationItems: annotations) {
            //MapPin(coordinate: $0.coordinate)
            MapPin(coordinate: $0.coordinate)
        }
        .frame(width: 400, height: 300)
    }
}

struct City: Identifiable {
    let id = UUID()
    let name: String
    let coordinate: CLLocationCoordinate2D
}
