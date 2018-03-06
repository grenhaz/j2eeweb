# J2EE DEMO WEB
## Tecnologías
- Spring MVC 4.3.7
- Spring Security 5.0.0
- Hibernate 5.2.13
## Configuración de la Base de datos
## Creación de la demo
Una vez realizado el despliegue de la web si accedemos a "[WEB]/demo/create"
se generará una demo con datos de prueba.
## Accesos de la demo
### Usuario administrador
username: admin@test.com

password: password
### Usuario normal
username: user@test.com

password: password
## Posibles problemas
### Glashfish y JBOSS
Existe un conflicto entre la versión de "JBoss logging" que tiene el glashfish y las 
dependencias incluidas en el proyecto. Este conflicto produce un error tal que:

[...]

java.lang.NoSuchMethodError: org.jboss.logging.Logger.debugf(Ljava/lang/String;I)V]]

[...]

Para corregir este problema solamente es necesario reemplazar la librería "JBoss logging"
utilizada por glashfish por una actualizada.

Se incluye dentro del proyecto una versión correcta en doc/jboss-logging.jar, 
la cual si es copiada en el directorio [GLASHFISH]/modules sobreescribiendo la 
versión que genera el conflicto ya debería funcionar el proyecto sin errores.
