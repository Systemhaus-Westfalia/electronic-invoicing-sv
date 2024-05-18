/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sv.mh.fe;

import org.springframework.http.ResponseEntity;
import sv.mh.fe.controller.FirmarDocumentoController;
import sv.mh.fe.filter.FirmarDocumentoFilter;
import sv.mh.fe.utils.ResponseBody;
import sv.mh.fe.z_service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleSimpleApplication implements CommandLineRunner {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties

	@Autowired
	private HelloWorldService helloWorldService;

	@Autowired
	private FirmarDocumentoFilter firmarDocumentoFilter;

	@Autowired
	private FirmarDocumentoController firmarDocumentoController;

	@Override
	public void run(String... args) {
		ResponseEntity <?> firmar;
		getFirmarDocumentoFilter();
        firmar = this.firmarDocumentoController.firmar(this.firmarDocumentoFilter);
		ResponseBody responseBody = (ResponseBody) firmar.getBody();
		String body = (String) responseBody.getBody();

		System.out.println("Das Ergebnis ist:" + body);
        System.out.println(this.helloWorldService.getHelloMessage());
		if (args.length > 0 && args[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

	private void getFirmarDocumentoFilter() {
		this.firmarDocumentoFilter.setActivo(true);
		this.firmarDocumentoFilter.setNit("06140904181038");
		this.firmarDocumentoFilter.setPasswordPri("Port465fer");
		this.firmarDocumentoFilter.setPasswordPub("DTEcofia");
		this.firmarDocumentoFilter.setNombreFirma("COFIA, S.A. de C.V");
		this.firmarDocumentoFilter.setDteJson("{\"extension\":null,\"receptor\":{\"descActividad\":\"Cultivo de cereales excepto arroz y para forrajes\",\"codActividad\":\"01111\",\"correo\":\"\",\"nit\":\"050236237\",\"direccion\":{\"complemento\":\"USULUTAN\",\"municipio\":\"23\",\"departamento\":\"11\"},\"nombreComercial\":null,\"telefono\":null,\"nombre\":\"GERSON ALFONSO CRUZ ZELAYA\",\"nrc\":\"11111111\"},\"identificacion\":{\"codigoGeneracion\":\"01000002-0000-0000-0000-000001041189\",\"tipoContingencia\":5,\"numeroControl\":\"DTE-03-02689815-000000001002452\",\"tipoOperacion\":2,\"ambiente\":\"00\",\"fecEmi\":\"2024-04-16\",\"tipoModelo\":2,\"tipoDte\":\"03\",\"version\":3,\"tipoMoneda\":\"USD\",\"motivoContin\":\"Contigencia por fecha de factura\",\"horEmi\":\"15:52:12\"},\"resumen\":{\"totalNoSuj\":0,\"ivaPerci1\":0,\"descuNoSuj\":0,\"totalLetras\":\"QUINIENTOS SIETE 94/100\",\"ivaRete1\":0,\"subTotalVentas\":449.5,\"subTotal\":449.5,\"reteRenta\":0,\"tributos\":[{\"descripcion\":\"Impuesto al Valor Agregado 13%\",\"codigo\":\"20\",\"valor\":58.44}],\"pagos\":[{\"codigo\":\"05\",\"periodo\":90,\"plazo\":\"01\",\"montoPago\":507.94,\"referencia\":\"Transferencia_ Deposito Bancario\"}],\"descuExenta\":0,\"totalDescu\":0,\"numPagoElectronico\":null,\"descuGravada\":0,\"porcentajeDescuento\":0,\"totalGravada\":449.5,\"montoTotalOperacion\":507.94,\"totalNoGravado\":0,\"saldoFavor\":0,\"totalExenta\":0,\"totalPagar\":507.94,\"condicionOperacion\":2},\"cuerpoDocumento\":[{\"descripcion\":\"SUSTRATO PINDSTRUP LV 300L\",\"montoDescu\":0,\"codigo\":\"91024\",\"ventaGravada\":449.5,\"ventaNoSuj\":0,\"ventaExenta\":0,\"tributos\":[\"20\"],\"numItem\":1,\"noGravado\":0,\"psv\":0,\"tipoItem\":2,\"codTributo\":null,\"uniMedida\":59,\"numeroDocumento\":null,\"cantidad\":10,\"precioUni\":44.95}],\"otrosDocumentos\":null,\"ventaTercero\":null,\"apendice\":[{\"etiqueta\":\"Descripcion\",\"valor\":\" \",\"campo\":\"Info\"}],\"documentoRelacionado\":null,\"emisor\":{\"descActividad\":\"Cultivo de otros cereales excepto arroz y forrajeros n.c.p.\",\"tipoEstablecimiento\":\"02\",\"direccion\":{\"complemento\":\"6 Calle Oriente #4-12\",\"municipio\":\"11\",\"departamento\":\"05\"},\"codEstable\":null,\"codPuntoVenta\":null,\"nombre\":\"GRUPO COFIA\",\"codActividad\":\"01119\",\"codEstableMH\":null,\"correo\":\"SCalderon@gmx.de\",\"nit\":\"06140904181038\",\"nombreComercial\":\"GRUPO COFIA\",\"telefono\":\"79309098\",\"nrc\":\"2689815\",\"codPuntoVentaMH\":null}}");

		System.out.println("Das JSON ist:" + this.firmarDocumentoFilter.getDteJson());
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleSimpleApplication.class, args);
	}

}
