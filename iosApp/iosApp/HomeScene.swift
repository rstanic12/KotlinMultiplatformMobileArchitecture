//
//  HomeScene.swift
//  iosApp
//
//  Created by Benjamin Mecanović on 14.04.2023..
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScene: View {
  let component: HomeComponent
  
  @ObservedObject
  private var activeTab: ObservableValue<DefaultHomeComponent.TabConfig>
  
  // TODO: -> Try to emit navigation via Dispatcher instead of calling navigation on a Concrete Component
  private let dispatcher: ActionDispatcher
  
  init(component: HomeComponent) {
    self.component = component
    activeTab = ObservableValue(component.activeTab)
    dispatcher = component.actionDispatcher
  }
  
    var body: some View {
      TabView(selection: .init(get: { activeTab.value }, set: { component.navigate(tab: $0) })) {
        Text("My Car")
          .tag(DefaultHomeComponent.TabConfigMyCar())
          .tabItem {
            Image(systemName: "car")
          }
        
        Text("Driving Activites")
          .tag(DefaultHomeComponent.TabConfigDrivingActivities())
          .tabItem {
            Image(systemName: "flag.fill")
          }
        
        Text("Map")
          .tag(DefaultHomeComponent.TabConfigMap())
          .tabItem {
            Image(systemName: "map")
          }
        
        Text("HVAC")
          .tag(DefaultHomeComponent.TabConfigHvac())
          .tabItem {
            Image(systemName: "car.side.air.circulate")
          }
        
        Text("Battery")
          .tag(DefaultHomeComponent.TabConfigBattery())
          .tabItem {
            Image(systemName: "bolt")
          }
      }
    }
}
