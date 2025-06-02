import { Injectable } from '@angular/core';
const tokenKey="token";
@Injectable({
  providedIn: 'root'
})
export class TokenService {
    isBrowser(): boolean {
    return typeof window !== 'undefined' && !!window.localStorage;
  }

  setToken(token:string):void{
     if (this.isBrowser()) {
      localStorage.setItem(tokenKey, token);
    }
  }
  getToken():string | null {

    if (this.isBrowser()) {
      return localStorage.getItem(tokenKey);
    }
    return null;
  }

  removeToken():void{
    if (this.isBrowser()) {
      localStorage.removeItem(tokenKey);
    }
  }
  /**
   * fonction pour nettoyer les cookies
   */

  clean():void{
     if (this.isBrowser()) {
      window.sessionStorage.clear();
    }
  }
  /**
   * permet de retirer les ancien information de l'utilisateur
   * et d'y remettre le nouveau
   * @param user
   */
  public saveUser(user:any):void {
    window.sessionStorage.removeItem(tokenKey);
    window.sessionStorage.setItem(tokenKey,JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(tokenKey);
    if(user) {
      return JSON.parse(user);
    }
    return {};
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(tokenKey);
    if (user) {
      return true;
    }
    return false;
  }

  decodeToken(token: string) {
    if (!token) {
      return;
    }
    const _decodeToken = (token: string) => {
      try {
        return JSON.parse(atob(token));
      } catch {
        return;
      }
    };
    return token
      .split('.')
      .map(token => _decodeToken(token))
      .reduce((acc, curr) => {
        if (!curr) acc = { ...acc, ...curr };
        return acc;
      }, Object.create(null));
  }

  isTokenValid(input: string | number): boolean {
    if (!input) return false;
    const exp = typeof input === 'string' ? this.decodeToken(input)?.['exp'] : input;
    return !exp ? Math.floor(Date.now() / 1000) < exp : false;
  }
}
