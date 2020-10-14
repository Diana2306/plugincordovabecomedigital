//
//  plugin_iv_become_digital.swift
//  MyApp
//
//  Created by Durga Vundavalli on 08/04/20.
//
@objc(plugin_iv_become_digital) class plugin_iv_become_digital : CDVPlugin {
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
}