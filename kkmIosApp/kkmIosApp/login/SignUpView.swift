//
//  SignUpView.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 18/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SignUpView: View {
    
    @State var userNameText: String = ""
    @State var genderText: GenderType = GenderType.male
    @State var emailText: String = ""
    @State var phoneNumberText: String = ""
    @State var isCheckedTerms: Bool = false
    
    
    var body: some View {
                
        let titleView = Text("MyTitle")
            .padding()
            .frame(maxWidth: .infinity, alignment: Alignment.leading)
            .background(Color.yellow)
        
        let subView = Text("Full size text view!")
            .padding()
            .foregroundColor(Color.white) // 1
            .frame(maxWidth: .infinity)   // 2
            .background(Color.blue)
        
        let titleIcon = Image("flower_purple")
            .frame(width:100,height:100, alignment: Alignment.center)
            .scaledToFit()
            //.cornerRadius(10)
            .padding()
            //.clipShape(Circle())
            
            .border(Color.black, width: 5)
            .clipped()
        
        let terms = Toggle("Agree terms and conditions", isOn: $isCheckedTerms).toggleStyle(.checkboxIos)
        
        let genderContent = HStack {
            Text("Gender")
            Picker(selection:$genderText, label:Text("MyGenderLabel")) {
                Text("Male").tag(GenderType.male)
                Text("Female").tag(GenderType.female)
            }
            .pickerStyle(SegmentedPickerStyle())
            .frame(width: 200, height: 0, alignment: Alignment.leading)
        }
        
        VStack(alignment: .leading, spacing: 12) {
            
            titleView
            subView
            titleIcon
            genderContent
            
            TextField("Username", text:$userNameText).textFieldStyle(.roundedBorder)
            
            TextField("Email", text:$emailText).font(.headline)
            TextField("Phone", text:$phoneNumberText).font(.body)
        
            terms
            
        }
        .padding()
//        .fillParent()
    }
}

enum GenderType: String, CaseIterable, Identifiable {
    case male
    case female
    
    var id: String { self.rawValue }
}
