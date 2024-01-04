//
//  Home.swift
//  iosApp
//
//  Created by 김용현 on 2024/01/03.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject var viewModel = HomeViewModelWrapper()

    var body: some View {
        VStack {
            Text("Hello")
            List(viewModel.posts, id: \._id) { post in
                Text(post.title)
            }
        }
        .task {
            print("위에임 ")
            print(viewModel.posts.count)
            
            // 여기에서 2초 딜레이 후 추가 작업을 수행합니다.
            DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                // 2초 후에 수행할 작업
                print("2초 후")
                print(viewModel.posts.count)
            }
        }
    }
}


class HomeViewModelWrapper: ObservableObject {
    private let kotlinViewModel: HomeViewModel

    @Published var posts: [Post] = []

    init() {
        kotlinViewModel = HomeViewModel()
        // 여기에서 kotlinViewModel의 상태 변화를 관찰하고 posts를 업데이트합니다.
    }
    
    func getTest() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0) {
            kotlinViewModel.fetchAllPosts()
            posts = kotlinViewModel.state.value
        }
       
    }
}


/*
class HomeViewModel: ObservableObject {
    @Published private(set) var response: [Post] = []

    private var kotlinViewModel = HomeViewModel()

    @MainActor
    func fetchAllPosts() async {
        kotlinViewModel.fetchAllPosts { posts in
            DispatchQueue.main.async {
                self.response = posts
            }
        }
    }
}

 */


