//
//  RootScene.swift
//  iosApp
//
//  Created by Benjamin Mecanović on 14.04.2023..
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RootScene: View {
  let component: RootComponent
  
  @ObservedObject
  private var childStack: ObservableValue<ChildStack<AnyObject, RootComponentChild>>
  
  // Does this need to be a component? Can we just pass a ViewModel wrapped as a Value as part of the Root component and then just inject the ViewModel to a View which will display whatever the ViewModel provides?
  @ObservedObject
  private var banner: ObservableValue<ChildSlot<AnyObject, RootComponentSlotChild>>
  
  private var activeChild: RootComponentChild { childStack.value.active.instance }
  
  init(_ component: RootComponent) {
    self.component = component
    childStack = ObservableValue(component.stack)
    banner = ObservableValue(component.slot)
  }
  
  var body: some View {
    ChildView(child: activeChild)
    
    if banner.value.child?.instance is RootComponentSlotChild.InternetBannerChild {
      Text("Banner")
    }
  }
}

private struct ChildView: View {
    let child: RootComponentChild
    
    var body: some View {
        switch child {
        case let child as RootComponentChild.HomeChild: HomeScene(component: child.component)
        case let child as RootComponentChild.LoginChild: LoginScene(component: child.component)
        default: EmptyView()
        }
    }
}
