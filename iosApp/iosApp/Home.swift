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
import WebKit


struct HomeScreenView: View {
    @StateViewModel var viewModel =  HomeViewModel()
    @State private var searchQuery: String = ""
    var webview: some View {
//        WebView(url: URL(string: "https://www.naver.com")!)
//        WebView(url: URL(string: "http://172.30.1.24:8080/posts/post?postId=658bb0f9e2b2aa68691fa9c4&showSections=false")!)
        WebView(url: URL(string: "http://172.30.1.24:8080/posts/post?postId=659c9ef41c6e5612ae25e56f&showSections=false")!)
            .navigationBarTitle("Details", displayMode: .inline)
            .edgesIgnoringSafeArea(.bottom)
    }
    var body: some View {
        webview
        NavigationView {
            List(viewModel.allPosts, id: \._id) { post in
                PostCardView(post: post)
            }
//            .navigationTitle("Blog")
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button(action: {
                        // 메뉴 액션 구현
                    }) {
                        Image(systemName: "line.horizontal.3")
                    }
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        // 검색 액션 구현
                    }) {
                        Image(systemName: "magnifyingglass")
                    }
                }
            }
//            .searchable(text: $searchQuery)
//            .onChange(of: searchQuery) { newValue in
//                // The viewModel.searchPostsByTitle needs to be called on the main thread.
//                DispatchQueue.main.async {
//                    viewModel.searchPostsByTitle(query: newValue)
//                }
//            }
        }
    }
}

struct SearchView: View {
    @Binding var searchQuery: String
    var viewModel: HomeViewModel

    var body: some View {
        VStack {
            TextField("검색어를 입력하세요", text: $searchQuery)
                .padding()
            
            if viewModel.searchedPosts.isEmpty {
                Text("검색 결과가 없습니다.")
            } else {
                PostCardsView(posts: viewModel.searchedPosts)
            }
        }
        .padding()
    }
}

// WebView를 SwiftUI에서 사용할 수 있도록 하는 뷰
struct WebView: UIViewRepresentable {
    let url: URL
    
    func makeUIView(context: Context) -> WKWebView {
        return WKWebView()
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        let request = URLRequest(url: url)
        uiView.load(request)
    }
}

// 웹뷰를 표시하는 상세 화면 뷰
struct DetailsView: View {
    let url: URL
    var body: some View {
        WebView(url: url)
            .navigationBarTitle("Details", displayMode: .inline)
            .edgesIgnoringSafeArea(.bottom)
    }
}

// PostCardsView에서 PostCard를 선택했을 때 상세 화면으로 이동
struct PostCardsView: View {
    var posts: [Post]

    var body: some View {
        List(posts, id: \._id) { post in
            NavigationLink(destination: DetailsView(url: URL(string: "http://172.30.1.24:8080/posts/post?postId=\(post._id)&showSections=false")!)) {
                PostCardView(post: post)
            }
        }
    }
}

struct PostCardView: View {
    let post: Post
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            if let imageUrl = URL(string: post.thumbnail) {
                AsyncImageView(url: imageUrl)
                    .aspectRatio(contentMode: .fill)
                    .frame(height: 200)
                    .clipped()
            } else {
                Color.gray // 이미지 로딩 중 표시할 뷰
                    .frame(height: 200)
            }
            
            Text(String(post.date))
                .font(.caption)
                .foregroundColor(.secondary)
            
            Text(post.title)
                .font(.headline)
                .lineLimit(1)
            
            Text(post.subtitle)
                .font(.subheadline)
                .lineLimit(2)
                .foregroundColor(.secondary)
            
            HStack {
                Text(post.category)
                    .font(.caption)
                    .padding(4)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(4)
            }
        }
        .padding(.vertical)
    }
}
