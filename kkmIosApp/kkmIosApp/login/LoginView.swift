//
//  LoginView.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 18/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LoginView: View {
    
    var body: some View {
        let titleIcon = Image("flower_purple")
            .frame(width:100,height:100, alignment: Alignment.topLeading)
            .clipShape(Circle())
                        .shadow(radius: 10)
                        .overlay(Circle().stroke(Color.red, lineWidth: 5))
        
        VStack {
        HStack(alignment: .top) {
            Text("One").aspectRatio(contentMode: .fit).background(Color.white)
            Text("Two").aspectRatio(contentMode: .fill).background(Color.green)
            Text("Three").aspectRatio(contentMode: .fit).background(Color.blue)
//            VStack(alignment: .leading) {
//                titleIcon
//
//                Text("Login Form Here")
//            }.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: Alignment.top).background(Color.green)
            Spacer(minLength: 50)
        }
        .padding()
        //.frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.yellow)
            
            Spacer()
    }
        .padding()
        //.navigationBarHidden(true)
        .navigationTitle("Login")
        .navigationBarTitleDisplayMode(.inline)
        .background(Color.gray)
    }
    
}
