
@objc(plugin_iv_become_digital) 
class plugin_iv_become_digital : CDVPlugin {
    @objc(coolMethod:)
    func coolMethod(_ command: CDVInvokedUrlCommand) {
        
        let msg = command.arguments[0] as? String ?? "Error"
        print(msg)
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR,
            messageAs: msg
        )
        if msg.count > 0 {
            pluginResult = CDVPluginResult(
                status: CDVCommandStatus_OK,
                messageAs: msg
            )
        }
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    @objc(setBecomeCallback:)
    func setBecomeCallback(_ command: CDVInvokedUrlCommand) {
        
        let clientId = command.arguments[0] as? String ?? ""
        let clientSecret = command.arguments[1] as? String ?? ""
        let contractId = command.arguments[2] as? String ?? ""
        let validationTypes = command.arguments[3] as? String ?? ""
        let allowLibraryLoading = command.arguments[4] as? Boolean ?? false
        let customerLogo = command.arguments[5] as? String ?? ""
        
        let becomeIdentity = BDIVVC(
            clientId: clientId,
            clientSecret: clientSecret,
            contractId: contractId,
            validationTypes: validationTypes,
            allowLibraryLoading: allowLibraryLoading,
            customerLogo: UIImage(named: customerLogo)!)
        becomeIdentity.modalPresentationStyle = .fullScreen
        present(becomeIdentity, animated: true, completion:{
            BDIVVC.onDoneBlock = { result in
                //Objeto final de la respuesta para obter todos los parametros retornados por la SDK
                let resultFinal = result as! BecomeDIVResponse.
                print(result)
                
                self.commandDelegate!.send(
                    CDVPluginResult(
                        status: CDVCommandStatus_OK,
                        messageAs: resultFinal
                    ),
                    callbackId: command.callbackId
                )
            }
        })
      
    }
}
