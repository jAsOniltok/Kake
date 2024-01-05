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
    @State var count = 0
    
    var postTitle: String {
        viewModel.state.first?.title ?? "No Title"
    }

    var body: some View {
        VStack {
            Text("제목: " + postTitle)
            Text("포스트 수: \(viewModel.state.count)")
            Text("포스트 수: \(count)")
            Button("Fetch Posts") {
                viewModel.fetchAllPosts()
                count = viewModel.getStateValue().count
                print("dmdkdkdkdkdkdk")
                print(count)
            }
        }
        .task {
            viewModel.fetchAllPosts()
            count = viewModel.getStateValue().count
            print("dmdkdkdkdkdkdk")
            print(count)
           
        }
        .onAppear {
            viewModel.fetchAllPosts()
            count = viewModel.getStateValue().count
            print("dmdkdkdkdkdkdk")
            print(count)
        }
    }
}
