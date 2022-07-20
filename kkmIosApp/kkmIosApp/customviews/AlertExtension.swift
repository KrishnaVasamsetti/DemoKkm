//
//  AlertExtension.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 20/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

public extension View {
    func alert(isPresented: Binding<Bool>,
               title: String,
               message: String? = nil,
               dismissButton: Alert.Button? = nil) -> some View {

        alert(isPresented: isPresented) {
            Alert(title: Text(title),
                  message: {
                    if let message = message { return Text(message) }
                    else { return nil } }(),
                  dismissButton: dismissButton)
        }
    }
}
