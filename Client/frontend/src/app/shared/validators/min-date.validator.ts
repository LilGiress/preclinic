import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";
/**
 * 🧩 Validator personnalisé pour interdire les dates passées
 * @param minDate Date minimale autorisée (ex: aujourd'hui)
 */
export function minDateValidators(minDate: Date):ValidatorFn {
  return (control:AbstractControl):ValidationErrors | null => {
    const value = control.value;
    if(!value) return null;

    const inputDate = new Date(value);
    // Supprimer l'heure pour comparaison juste sur la date
    const today = new Date(minDate);
    today.setHours(0,0,0,0,);
    inputDate.setHours(0,0,0,0);
    return inputDate < today ? { pastDate: true } : null;
  };

}
