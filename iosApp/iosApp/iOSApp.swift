import SwiftUI
import shared

@main
struct iOSApp: App {
  @UIApplicationDelegateAdaptor(AppDelegate.self)
  var appDelegate: AppDelegate
  
  @Environment(\.scenePhase)
  var scenePhase: ScenePhase
  
  private var rootHolder: RootHolder { appDelegate.rootHolder }
  
  var body: some Scene {
    WindowGroup {
      RootScene(rootHolder.root)
        .onChange(of: scenePhase) { newPhase in
          switch newPhase {
          case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
          case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
          case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
          @unknown default: break
          }
        }
    }
  }
}

class AppDelegate: NSObject, UIApplicationDelegate {
  fileprivate let rootHolder = RootHolder(savedState: nil)
}

private class RootHolder {
  let lifecycle: LifecycleRegistry
  let root: RootComponent
  
  init(savedState: ParcelableParcelableContainer?) {
    lifecycle = LifecycleRegistryKt.LifecycleRegistry()
    
    root = DefaultRootComponent(
      componentContext: DefaultComponentContext(
        lifecycle: lifecycle,
        stateKeeper: nil,
        instanceKeeper: nil,
        backHandler: nil
      )
    )
    
    LifecycleRegistryExtKt.create(lifecycle)
  }
  
  deinit {
    LifecycleRegistryExtKt.destroy(lifecycle)
  }
}
