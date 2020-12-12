# plugincordovabecomedigital

La siguiente documentación corresponde a la integración del plugin que conecta la SDK android.

## Agregar plugin a la solución ionic
Para la instalación del plugin debe utilizar el comando **plugman**, los pasos para su uso podrá encontrarlos en la siguiente dirección de internet:

  https://cordova.apache.org/docs/es/latest/plugin_ref/plugman.html

## Agregar el plugin becomedigital a su plataforma android

Para realizar este paso debe utilizar el siguiente comando:

    plugman install --platform android --project platforms/android --plugin

## Requerimientos necesarios para el uso del plugin

**1. Debe integrar en la plataforma Andorid la SDK de Become Android.**

     Dirección de referencia: 
   
   https://github.com/Becomedigital/become_ANDROID_SDK
		 
**2. Agregar los siguientes adaptadores para soportar el sistema Android X:**

    Direcciones de referencia:
   https://github.com/dpa99c/cordova-plugin-androidx
   
   https://github.com/dpa99c/cordova-plugin-androidx-adapter

## Llamado al plugin desde ionic
El siguiente fragmento de código indica como realizar el llamado al plugin desde ionic:

    import { Component, NgZone } from "@angular/core";
    import { Platform } from "@ionic/angular";

    //global instance of cordova
    declare var cordova: any;
    @Component({
      selector: "app-home",
      templateUrl: "home.page.html",
      styleUrls: ["home.page.scss"],
    })
    
    export class HomePage {
      constructor(public platform: Platform, zone: NgZone) {
        platform.ready().then(() => {
          cordova.plugins.plugin_iv_become_digital.initBecome({
            // Datos de configuración proporcionados por el proveedor
            clientId: "abc_def",
            clientSecret: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            contractId: "",
            validatiopnTypes: "A/B/C",
            useGallery: true,
          });

          cordova.plugins.plugin_iv_become_digital.setBecomeCallback(
            (response) => {
              console.log("setBecomeCallback success: " + response);
            },
            (error) => {
              console.log("setBecomeCallback error: " + error);
            }
          );
          console.log("ngOnInit - Home Page");
        });
      }
    }

    
## Respuesta del plugin

La respuesta dada por el plugin se recibe en un objeto JSON compuesto por los siguientes campos:

    {
      "id": "id",
      "created_at":"created_at",
      "company":"company",
      "fullname":"fullname",
      "birth", "birth",
      "document_type", "document_type",
      "document_number", "document_number",
      "face_match", "face_match",
      "template", "template",
      "alteration", "alteration",
      "watch_list", "watch_list",
      "comply_advantage_result", "comply_advantage_result",
      "comply_advantage_url", "comply_advantage_url",
      "verification_status", "verification_status",
      "message", "message",
      "responseStatus": "responseStatus"
    }

