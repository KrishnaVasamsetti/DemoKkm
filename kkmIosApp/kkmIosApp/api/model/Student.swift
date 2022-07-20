//
//  Student.swift
//  kkmIosApp
//
//  Created by Vasamsetti Pavana Venkata Krishna  on 20/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

struct Student: Codable, Identifiable {
    var id: Int
    var name: String
    var age: Int
    var college_name: String
}
