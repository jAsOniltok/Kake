//
//  Home.swift
//  iosApp
//
//  Created by 김용현 on 2024/01/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import KMMViewModelCore
import KMMViewModelSwiftUI

struct HomeScreen: View {
    
    @StateViewModel var viewModel = HomeViewModel()
    
    var postTitle: String {
        viewModel.allPosts.first?.title ?? "No Title"
    }

    var body: some View {
        VStack {
            Text("제목: " + postTitle)
            Text("포스트 수: \(viewModel.allPosts.count)")
    
        }
        .task {
            viewModel.fetchAllPosts()
        }
    }
}
