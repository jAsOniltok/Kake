//
//  Collector.swift
//  iosApp
//
//  Created by 김용현 on 2024/01/04.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class Collector<T> : Kotlinx_coroutines_coreFlowCollector {
    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }
    
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value as! T)
        completionHandler(nil)
    }
}
