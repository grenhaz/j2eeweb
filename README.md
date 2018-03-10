# J2EE DEMO WEB
## Tecnologías J2EE
- Spring MVC 4.3.7
- Spring Security 5.0.0
- Hibernate 5.2.13
## Tecnologías Cliente
- jQuery
- Bootstrap 3
- FontAwesome 4.7
- DataTables
- TinyMCE 4
## IDE's
- Netbeans 8.x
- Eclipse J2EE IDE
## Web Application Server's
- Glassfish 4.1
- Tomcat 8.0
## Configuración básica
### Netbeans 8.x
Se puede utilizar un gestor de BBDD Derby por su simplicidad a la hora de ser instalada en NetBeans y configurar la web para conectarse a ella.
### Eclipse
Al igual que ocurre con NetBeans se puede utilizar un gestor de BBDD Derby.
## Configuración de la web
Los parámetros de configuración de la Web, la Base de Datos, el servidor de correo y otros parámetros se especifica en el fichero src/main/resources/properties/config.properties.
## Creación de la demo
Una vez realizado el despliegue de la web si accedemos a "[WEB]/demo/create" se generará una demo con datos de prueba.
![Preview]https://raw.githubusercontent.com/grenhaz/j2eeweb/branch/doc/preview.jpg
## Accesos de la demo
### Usuario administrador
username: admin@test.com

password: password
### Usuario normal
username: user@test.com

password: password
## Posibles problemas
### Glashfish y JBOSS
Existe un conflicto entre la versión de "JBoss logging" que tiene el glashfish y las dependencias incluidas en el proyecto. Este conflicto produce un error tal que:

[...]

java.lang.NoSuchMethodError: org.jboss.logging.Logger.debugf(Ljava/lang/String;I)V]]

[...]

Para corregir este problema solamente es necesario reemplazar la librería "JBoss logging" utilizada por glashfish por una actualizada.

Se incluye dentro del proyecto una versión correcta en doc/jboss-logging.jar, la cual si es copiada en el directorio [GLASHFISH]/modules sobreescribiendo la versión que genera el conflicto ya debería funcionar el proyecto sin errores.