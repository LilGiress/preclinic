import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {AuthService} from "../../services/auth/auth.service";
import {catchError, throwError} from "rxjs";
import {inject, Injectable} from "@angular/core";
import { ModalService } from '../../services/modal.service';
import { Router } from '@angular/router';


export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const modalService = inject(ModalService);
  const authService = inject(AuthService);
  const router = inject(Router);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let message = 'Une erreur inattendue est survenue';
      let title = `Erreur ${error.status}`;

      if (error.error instanceof ErrorEvent) {
        // --- 1. ERREUR CÔTÉ CLIENT (Problème réseau, etc.) ---
        message = `Problème de connexion : ${error.error.message}`;
      } else {
        // --- 2. ERREUR CÔTÉ SERVEUR (Le backend a répondu) ---
        
        // Tentative de récupération du message précis envoyé par le backend Java
        // Souvent structuré comme : { "message": "...", "status": 400 }
        const backendMessage = error.error?.message || error.error?.error;

        switch (error.status) {
          case 0:
            message = "Le serveur est injoignable. Vérifiez votre connexion.";
            title = "Serveur Hors-ligne";
            break;
          case 400:
            message = backendMessage || "Les données envoyées sont incorrectes.";
            break;
          case 401:
            message = "Votre session a expiré. Veuillez vous reconnecter.";
            title = "Non autorisé";
            authService.logout(); // On vide le localStorage/Session
            router.navigate(['/login']); // Redirection immédiate
            break;
          case 403:
            message = "Vous n'avez pas les droits nécessaires pour effectuer cette action.";
            title = "Accès refusé";
            break;
          case 404:
            message = "La ressource demandée (conge, utilisateur, etc.) est introuvable.";
            break;
          case 500:
            message = "Le serveur a rencontré un problème interne. Réessayez plus tard.";
            title = "Erreur Serveur";
            break;
          default:
            message = backendMessage || `Une erreur ${error.status} est survenue.`;
        }
      }

      // Affichage de l'alerte
      modalService.openWarning(message, title);

      // On renvoie l'objet error complet pour que le .subscribe({ error: ... }) 
      // du composant puisse arrêter un spinner par exemple.
      return throwError(() => error);
    })
  );
};
