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
        let app = AppDelegate()
        let initiate = app.application
        print(initiate)
        let sn = "TiHeSBp9BlLKFW6jgfondDy9ovkoUGwbsFLES3HYkRPQ48KL5aZe9dMtLCS/I44M/wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//igVOBiVnz7W2LScEbB3TdUWjQH+gTO5sfaexY2pOf+39SoNpk07DhbbEfkoqemCgrwp++6XTAcupECUJbt9NgQ4qZlVxTNm7OgQ8TBSIt71KxQfNH0iMUHJ0KRMwBVXlysgpW7Tzt+sl3GoX3zF6n/BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR/+i/cVq1OcLiYGSr/pfPSVQ=a8677206db3ee35f1c4a1700eaa2c8d7";
        let v = app.scanAndConnect(sn:sn)
        let c = app.lockerStatus(sn: sn)
        print(c)
        let pass = app.getPass(sn: sn)
        call.success([
            "value": pass
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
    var sdk = AirbnkSDK() // AirbnkSDKDelegate()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        AirbnkSDK.initAirbnkSDK("airbnkHaha123456")
        return true
    }
        
    func scanAndConnect(sn:String) -> () {
         AirbnkSDK.connect(toLock: sn)
    }
    func unlock(sn:String) -> (){
        AirbnkSDK.unlock(sn)
    }
    func lockerStatus(sn:String) -> (){
        AirbnkSDK.getLockStatus(sn)
    }
    func getPass(sn:String) -> (){
        AirbnkSDK.getOneTimePassWord(sn)
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
