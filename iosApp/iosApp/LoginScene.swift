//
//  LoginScene.swift
//  iosApp
//
//  Created by Benjamin Mecanović on 14.04.2023..
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginScene: View {
  let component: LoginComponent
  
  @ObservedObject
  private var viewModel: ObservableValue<LoginViewModel>
  private let dispatcher: ActionDispatcher
  
  init(component: LoginComponent) {
    self.component = component
    viewModel = ObservableValue(component.viewModel)
    dispatcher = component.actionDispatcher
  }
  
  var body: some View {
    VStack {
      Button("Login") {
        dispatcher.emit(action: LoginAction.LoginPressed())
      }
    }
  }
}
