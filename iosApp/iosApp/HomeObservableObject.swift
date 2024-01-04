//
//  HomeObservableObject.swift
//  iosApp
//
//  Created by 김용현 on 2024/01/03.
//  Copyright © 2024 orgName. All rights reserved.
//\

//import Foundation
//import Combine
//import shared
//
//public class HomeObservableObject: ObservableObject {
//    var viewModel: HomeViewModel
//
//    // StateFlow의 데이터를 담을 @Published 변수
//    @Published var posts: [Post] = []
//
//    init(wrapped: HomeViewModel) {
//        viewModel = wrapped
//        setupStatePublisher()
//    }
//
//    private func setupStatePublisher() {
//        // 여기에서 Kotlin의 StateFlow를 관찰하고, 변화가 있을 때마다 posts를 업데이트합니다.
//        // 주의: Kotlin의 StateFlow를 Swift에서 직접 사용하는 것은 복잡할 수 있으므로,
//        // 적절한 방식으로 데이터를 전달하도록 설정해야 합니다.
//    }
//
//    func fetchAllPosts() {
//        viewModel.fetchAllPosts()
//    }
//}
