package org.obarcia.demo.services;

import java.util.List;
import org.obarcia.demo.models.browser.FileBrowser;

/**
 * Servicio del explorador de archivos.
 * 
 * @author obarcia
 */
public interface BrowserService
{
    /**
     * Devuelve un listado de avatares.
     * @return Listado de avatares.
     */
    public List<String> getAvatars();
    /**
     * Devuelve un listado de ficheros.
     * @param path Path de busqueda.
     * @param type Tipo de ficheros.
     * @return Listado de ficheros.
     */
    public List<FileBrowser> getFiles(String path, String type);
}
