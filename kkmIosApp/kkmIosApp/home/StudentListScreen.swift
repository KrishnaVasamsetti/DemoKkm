//
//  StudentListScreen.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 20/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct StudentListScreen: View {
    
    @State var students = [Student]()
    @State var isInProgress = false
    @State var hasError = false
    @State var errorMessage = ""
    
    func loadStudentDetails() {
        isInProgress = true
        ApiService().getStudentList(completion: {
            students in
                print("My Students")
                self.students = students
                isInProgress = false
        }, errorEvent: { error in
            errorMessage = error
            isInProgress = false
            hasError = true
        })
    }
    
    var body: some View {
//        NavigationView {
        VStack{
            if(isInProgress) {
                ProgressView("Loading")
                //.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
            } else {
                List(students) { student in
                    VStack(alignment: .leading) {
                        Text("Id: ")+Text(" \(student.id)").bold()
                        Text("Name: ")+Text(" \(student.name)").bold()
                        Text("Age: ")+Text(" \(student.age)").bold()
                        Text("College: ")+Text("\(student.college_name)").bold()
                    }
                    
                }
            }
            
            
            //Spacer()
        }
        .alert(isPresented: $hasError, title: errorMessage)
        .onAppear() {
            loadStudentDetails()
        }
        .navigationTitle("Students")
        .navigationBarTitleDisplayMode(.inline)
    }
//    }
    
}
