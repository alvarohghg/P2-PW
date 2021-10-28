Cuando ejecutas el programa ya sea desde eclipse o con desde consola con el .jar, lo primero que nos sale es un menú donde
podemos hacer 2 cosas. Registrarse o iniciar sesión
Si decidimos registrarnos, vamos a tener que introducir por consola el correo que debe ser único, el nombre los apellido, el nick y el tipo de usuario que eres
Si iniciamos sesión deberemos introducir el correo, una vez iniciado sesión nos saldra el menu de las operaciones que podemos realizar
Si eres administrador obtendremos por pantalla:

	Dar de alta un espectáculo: Nos pregunta en primer lugar el tipo de espectáculo (temporada, puntual o múltiple). Para todos los espectáculos, por defecto nos preguntará por el título, la descripción, 
        la categoría (la cual deberemos seleccionar mediante un rango numérico), el aforo disponible y las localidades vendidas. En el caso del espectáculo puntual, solo nos preguntará por una fecha (que introduciremos según el formato [“yyyy-mm-dd”]; 
        en el caso del espectáculo de temporada, debemos seleccionar el día de la semana (mediante números), la fecha de inicio y la de fin; en el múltiple nos preguntará por la cantidad de fechas de sesiones que tendrá el espectáculo y a continuación 
        deberemos introducirlas. 

	Cancelar un espectáculo: En primer lugar debemos seleccionar si queremos eliminar una única sesión de un espectáculo o todas las sesiones al completo. Por defecto, nos pedirá para ambos casos el título del espectáculo y, en el segundo caso, la fecha de la sesión.
	
    	Actualizar un espectáculo: En este caso, introducimos el título del espectáculo y a continuación seleccionamos el aspecto que deseamos alterar de éste
	
    	Contabilizar la venta de entradas: Nos devuelve el titulo de los espectaculos que tienen entradas disponibles


Si eres espectador te saldra:
	Ver entradas disponibles para un espectáculo: Nos pide por consola el título del espectáculo y una fecha y nos devuelve el número de entradas que quedan para ese espectáculo y en esa fecha
    
	Buscar un espectaculo por titulo o categoría: Elegimos la opción que queremos, si elegimos título nos pide que se lo pasemos por consola, si existe un espectaculo con ese título nos lo imprime. 
        Si elegimos que queremos buscar por categoria elegimos la categoría que queremos pasandole 1, 2 o 3 segun la que elijamos y nos muestra todos los espectaculos con esa categoría 
    
	Buscar proximos espectaculo: Nos pregunta si queremos filtrar por categoría, si queremos indicamos por cual y nos imprime los espectáculos que estan disponibles cogiendo la fecha actual como fecha que le pasamos a la función
   
    	Publicar una crítica: Nos va pidiendo que vayamos introduciendo por consola los atributos de la crítica
   
    	Ver criticas: Nos muestra una lista del título de las críticas creadas, y escribiremos por consola el título de la crítica que queramos leer
   
    	Eliminar una crítica: Nos mostrará una lista de las críticas de las que somos autor y le pasaremos el título de la que queramos borrar
   
    	Valorar una critica: Nos muestra una lista de las críticas de otros autores, y escribimos por consola el título de la crítica que queremos votar y después la puntuación que le damos



