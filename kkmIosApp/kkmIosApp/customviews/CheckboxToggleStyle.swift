//
//  CheckboxView.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 20/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI


extension ToggleStyle where Self == CheckboxToggleStyle {

    static var checkboxIos: CheckboxToggleStyle {
        return CheckboxToggleStyle()
    }
}

struct CheckboxToggleStyle: ToggleStyle {
    func makeBody(configuration: Configuration) -> some View {
        return HStack {
            Image(systemName: configuration.isOn ? "checkmark.square" : "square")
                .renderingMode(.template)
                    .foregroundColor(.accentColor)
                .onTapGesture { configuration.isOn.toggle() }
            //Spacer()
            configuration.label
        }
    }
}
