//
//  ApiService.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 20/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation


class ApiService: ObservableObject {
    
    //let studentListUrl = "https://jsonkeeper.com/b/LOAL"
    let studentListUrl = "https://jsonkeeper.com/b/CPJ4"
    
    @Published var studentList = [Student]()
    
    func getStudentList(completion:@escaping ([Student])->(), errorEvent:@escaping (String)->()) {
        
        guard let url = URL(string: studentListUrl) else {
            print("Invalid URL")
            return
        }
        
        URLSession.shared.dataTask(with: url) { data, response, error in
            
            if let err = error {
              print("Error accessing swapi.co: /(error)")
              return
            }
            
            guard let httpResponse = response as? HTTPURLResponse,
                        (200...299).contains(httpResponse.statusCode) else {
                let httpRes = response.self as? HTTPURLResponse
                errorEvent("Status code: \(httpRes?.statusCode) Message: \(String(describing: httpRes?.url!))")
                print("Error with the response, unexpected status code: \(String(describing: response))")
                return
              }
            
            let students = try! JSONDecoder().decode([Student].self, from: data!)
            print(students)
            DispatchQueue.main.async {
                completion(students)
            }
        }.resume()
    }
}

