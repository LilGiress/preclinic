import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {isPlatformBrowser} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class ScriptLoaderService {
  private loadedScripts: Map<string, Promise<void>> = new Map();
  private loadedStyles: Set<string> = new Set();
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  loadStyle(href: string): Promise<void> {
    return new Promise((resolve) => {
      if (!isPlatformBrowser(this.platformId)) return resolve();

      if (this.loadedStyles.has(href)) return resolve();

      const link = document.createElement('link');
      link.rel = 'stylesheet';
      link.href = href;

      link.onload = () => resolve();
      link.onerror = () => resolve(); // On évite le blocage même si une feuille ne se charge pas

      document.head.appendChild(link);
      this.loadedStyles.add(href);
    });
  }

  loadScript(src: string): Promise<void> {
    if (!isPlatformBrowser(this.platformId)) {
      return Promise.resolve();
    }

    // Si déjà chargé : on renvoie la promesse existante
    if (this.loadedScripts.has(src)) {
      return this.loadedScripts.get(src)!;
    }

    const promise = new Promise<void>((resolve, reject) => {
      const script = document.createElement('script');
      script.src = src;
      script.async = false; // IMPORTANT pour garder l’ordre
      script.onload = () => resolve();
      script.onerror = () => reject(`ERROR loading script: ${src}`);

      document.body.appendChild(script);
    });

    this.loadedScripts.set(src, promise);
    return promise;
  }

  // Charge une liste de scripts dans l'ordre
  async loadScriptsSequentially(scripts: string[]): Promise<void> {
    for (const script of scripts) {
      await this.loadScript(script);
    }
  }

}
