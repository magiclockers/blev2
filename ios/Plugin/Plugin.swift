import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ContactsPlugin)
public class ContactsPlugin: CAPPlugin {

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.success([
            "value": value
        ])
    }
    @objc func getContacts(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.success([
            "value": value
        ])
    }

}
//import AirbnkSDK.h
@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        AirbnkSDK.initAirbnkSDK("airbnkHaha123456")
        return true
    }
}

//class LockViewController {
//    func scanAndConnect() {
//        hud = HudUtils.createMBProgressHUD(withStr: "", inView: view)
//        AirbnkSDK.connect(toLock: UserDefaults.standard.object(forKey: SNINFO_KEY))
//
//        hud = HudUtils.createMBProgressHUD(withStr: "", inView: view)
//        AirbnkSDK.unlock(UserDefaults.standard.object(forKey: SNINFO_KEY))
//    }
//}
