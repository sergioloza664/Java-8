package fechasYHoras;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class EjemploFechaHora {
	

	//Nombre		Tipo			Uso
	//----------------------------------------------------------------------------------------------------
	//of			estático		Crear una instancia del objeto a partir de los datos de entrada
	//from			estático		Convertir el parámetro de entrada en una instancia de la clase
	//parse			estático		Procesar la cadena de entrada y construir una instancia
	//format		instancia		Procesar los datos para producir una cadena de caracteres
	//get			instancia		Devolver una parte del objeto
	//is			instancia		Consultar el estado del objeto
	//with			instancia		Devolver una copia del objeto con uno o varios elementos modificados
	//plus			instancia		Devolver una copia del objeto con una cantidad de tiempo añadido
	//minus			instancia		Devolver una copia del objeto con una cantidad de tiempo sustraído
	//to			instancia		Convertir el objeto en otro Tipo
	//at			instancia		Combinar el objeto con otro objeto

	public static void main(String[] args) {
		//Fecha de hoy
		LocalDate hoy = LocalDate.now(); // Fecha actual
		System.out.println("La fecha de hoy es: " + hoy);
		System.out.println(hoy.getYear() + " / " + hoy.getMonthValue() + " / " + hoy.getDayOfMonth() + "\n");

		//Fecha especificada y fecha con mes y dia solo
		LocalDate nacimiento = LocalDate.of(1992, 03, 24); // Fecha completa creada con enteros
		System.out.println("La fecha de nacimiento es: " + nacimiento);

		
		MonthDay diaNacimiento = MonthDay.of(nacimiento.getMonthValue(), nacimiento.getDayOfMonth()); // Mes y dia																										// creada con																										// enteros
		System.out.println("El mes y dia de nacimiento es: " + diaNacimiento);

		//Mes y dia obtenidos de la fecha de hoy, un localDate
		MonthDay diaMesHoy = MonthDay.from(LocalDate.now()); // Mes y dia oftenidos de la fecha actual
		System.out.println("El mes y dia de hoy es: " + diaMesHoy);

		//Hora actual
		LocalTime horaActual = LocalTime.now(); // hora actual
		System.out.println("La hora actual es: " + horaActual);

		//Aumento de 2 horas de la actual hora
		LocalTime horaDespues = horaActual.plusHours(2); // Añade dos horas
		System.out.println("La hora denyro de 2 horas sera: " + horaDespues);

		//Aumentamos 1 semana de la fecha actual
		LocalDate sigSemana = LocalDate.now().plus(1, ChronoUnit.WEEKS); // Añade 1 semana a la fecha actual
		System.out.println("Dentro de una semana sera: " + sigSemana);

		// Comparar fechas
		if (hoy.isAfter(nacimiento)) {
			System.out.println("La fecha de hoy es posterior a la de nacimiento");
		}

		if (nacimiento.isBefore(hoy)) {
			System.out.println("La fecha de nacimiento es anterior a la de hoy");
		}

		// Fecha con el año y el mes
		YearMonth anyoMesActual = YearMonth.now();
		System.out.println("El año y mes de hoy es: " + anyoMesActual);
		YearMonth caducidadTarjetaCredito = YearMonth.of(2022, Month.DECEMBER);
		System.out.println("La tarjeta caduca en: " + caducidadTarjetaCredito);

		// Saber si un año es bisiesto
		if (hoy.isLeapYear()) {
			System.out.println("Este año es bisiesto: " + hoy);
		} else {
			System.out.println("Este año no es bisiesto: " + hoy);
		}

		// Periodo de tiempo entre fechas
		LocalDate java8 = LocalDate.of(2014, Month.MARCH, 14);
		LocalDate java9 = LocalDate.of(2017, Month.SEPTEMBER, 27);
		Period periodoVersionesJava = Period.between(java8, java9);
		System.out.println(
				"Periodo de duración entre java 8 y java 9 es de: " + periodoVersionesJava.toTotalMonths() + " meses");

		// Formatear fechas de String sin barras
		java.lang.String dia = "20171112";
		LocalDate fechaFormateada = LocalDate.parse(dia, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.printf("La fecha generada del String %s es %s \n", dia, fechaFormateada);

		// Formatear fechas de String con barras a LocalDate
		java.lang.String dia2 = "18/04/2014";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaFormat = LocalDate.parse(dia2, formatter);
			System.out.printf("La fecha formateada de %s es %s \n",dia2, fechaFormat);
		} catch (DateTimeParseException e) {
			System.out.printf("%s no es parseable \n", dia2);
			e.printStackTrace();
		}
		
		
		//Formatear fecha con la hora
		LocalDateTime hoyFecha = LocalDateTime.now();
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			java.lang.String hoyFechaFormateada = hoyFecha.format(format);
			System.out.printf("La fecha con la hora es: %s \n", hoyFechaFormateada);
		} catch (DateTimeException e) {
			System.out.printf("%s no puede ser formateada", hoyFecha);
			e.printStackTrace();
		}
		
		
		//Tiempo entre 2 horas
		LocalTime hora1 = LocalTime.of(23, 00);
		LocalTime hora2 = LocalTime.now();
		Duration duracion = Duration.between(hora1, hora2);
		System.out.println("La diferencia entre las 2 horas es de: " + duracion);
	}


}
