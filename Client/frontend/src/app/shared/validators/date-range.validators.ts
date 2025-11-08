import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

/**
 * 🧩 Validator personnalisé qui vérifie que endDate >= startDate
 * @param startControlName Nom du champ de date de début
 * @param endControlName Nom du champ de date de fin
 */
export function dateRangeValidators(
  startControlName: string,
  endControlName: string,
): ValidatorFn{
return (group:AbstractControl): ValidationErrors | null => {
  const start = group.get(startControlName)?.value;
  const end = group.get(endControlName)?.value;

  if (!start || !end) return null;

  const startDate = new Date(start);
  const endDate = new Date(end);

  return endDate >= startDate ? null :{dateRangeInvalid:true};
 };
}
