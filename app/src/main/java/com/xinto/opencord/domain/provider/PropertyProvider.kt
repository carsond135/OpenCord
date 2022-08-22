package com.xinto.opencord.domain.provider

import com.xinto.opencord.gateway.dto.IdentificationProperties
import com.xinto.opencord.rest.body.XSuperProperties

interface PropertyProvider {

    val xSuperProperties: XSuperProperties

    val identificationProperties: IdentificationProperties

}

class PropertyProviderImpl(
    private val telemetryProvider: TelemetryProvider
) : PropertyProvider {

    override val xSuperProperties: XSuperProperties =
        XSuperProperties(
            browser =
        )
}