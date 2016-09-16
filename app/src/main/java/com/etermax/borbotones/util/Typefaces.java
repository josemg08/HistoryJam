package com.etermax.borbotones.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * This class cache all the custom fonts that the app will use. This class was create in order to solve a
 * bug, see: http://code.google.com/p/android/issues/detail?id=9904#c7
 */
@SuppressWarnings("unused")
public class Typefaces {
	private static final String TAG = "Typefaces";
	private static final Hashtable<String, Typeface> cache = new Hashtable<>();

	/**
	 * Carga una font desde la carpeta assets
	 * @param c contexto que permite acceder a assets
	 * @param assetPath nombre de la font
	 * @return la fuente del cache, o recientemente creada
	 */
	public static Typeface get(Context c, String assetPath) {
		synchronized (cache) {
			if (!cache.containsKey(assetPath)) {
				try {
					Typeface t = Typeface.createFromAsset(c.getAssets(), "fonts/" + assetPath);
					cache.put(assetPath, t);
				} catch (Exception e) {
					return null;
				}
			}
			return cache.get(assetPath);
		}
	}
	
	/**
	 * Carga la font desde un archivo, se le tiene que pasar la ruta completa
	 * @param assetPath ruta del archivo a la font
	 * @return la fuente del cache, o recientemente creada
	 */
	public static Typeface get(String assetPath) {
		synchronized (cache) {
			if (!cache.containsKey(assetPath)) {
				try {
					Typeface t = Typeface.createFromFile(assetPath);
					cache.put(assetPath, t);
				} catch (Exception e) {
					return null;
				}
			}
			return cache.get(assetPath);
		}
	}
	
	/**
	 * Remueve del caché la fuente
	 * @param assetPath ruta del archivo a la font
	 */
	public static void removeFromCache(String assetPath) {
		if(cache.containsKey(assetPath)) {
			cache.remove(assetPath);
		}
	}
}
