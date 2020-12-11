/********* plugin_iv_become_digital.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface plugin_iv_become_digital : CDVPlugin {
  // Member variables go here.
}

- (void)coolMethod:(CDVInvokedUrlCommand*)command;
@end

@implementation plugin_iv_become_digital

- (void)coolMethod:(CDVInvokedUrlCommand*)command
{ 
    CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"ok test"];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
