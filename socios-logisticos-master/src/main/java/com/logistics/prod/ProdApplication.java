package com.logistics.prod;

import com.logistics.prod.Controllers.PartnerController;
import com.logistics.prod.Interfaces.IPartnerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.inject.Inject;
import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class ProdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdApplication.class, args);

		ProdApplication prodApplication = new ProdApplication();
		//prodApplication.receivePendingPackages();
		//prodApplication.changePackageTransit(1L);
		//prodApplication.getChangeStatus();
		//prodApplication.sendChangedStatus();

		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;

		while(!salir){
			System.out.println("***************************");
			System.out.println("*********vacunas.uy********");
			System.out.println("*******Nodo Logístico******");
			System.out.println("***************************");
			System.out.println("1. Recibir paquetes pendientes");
			System.out.println("2. Cambiar estado a InTransit");
			System.out.println("3. Cambiar estado a Delivered");
			System.out.println("4. Enviar cambios de estado ");
			System.out.println("6. Salir");

			try {
				System.out.println("Elija una opción: ");
				opcion = sn.nextInt();

				switch(opcion){
					case 1:

						prodApplication.receivePendingPackages();

						break;
					case 2:

						System.out.println("Ingrese ID de paquete: ");
						Long number1 = sn.nextLong();
						prodApplication.changePackageTransit(number1);


						break;
					case 3:

						System.out.println("Ingrese ID de paquete: ");
						Long number2 = sn.nextLong();
						prodApplication.changePackageDelivered(number2);
						break;

					case 4:
						System.out.println("Enviando cambios de estado");
						prodApplication.sendChangedStatus();

						break;

					case 5:

						break;

					case 6:
						salir=true;
						break;


					default:
						System.out.println("Solo números entre 1 y 6");
				}
			} catch (InputMismatchException e) {

			}

		}
	}



	@Inject
	IPartnerController partnerController = new PartnerController();

	private void sendChangedStatus(){
		partnerController.sendChangedStatus();
	}

	private void receivePendingPackages() {
		partnerController.receivePending(1L);
	}

	private void changePackageTransit(Long idPackage){
		partnerController.changePackageStatusTransit(idPackage);
	}

	private void changePackageDelivered(Long idPackage){
		partnerController.changePackageStatusDelivered(idPackage);
	}

	private void getChangeStatus(){
		partnerController.getChangeStatus();
	}


	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory(){
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("default");
		return factoryBean;
	}



}
