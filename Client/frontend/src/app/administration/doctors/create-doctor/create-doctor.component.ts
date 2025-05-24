import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { DoctorService } from '../../../services/doctors/doctor.service';

@Component({
  selector: 'app-create-doctor',
  standalone: true,
  imports: [],
  templateUrl: './create-doctor.component.html',
  styleUrl: './create-doctor.component.css'
})
export class CreateDoctorComponent implements OnInit {
  profileForm: FormGroup;

  constructor(private fb: FormBuilder, private profileService: DoctorService) {
    this.profileForm = this.fb.group({
      name: [''],
      specialization: [''],
      pricing: [''],
      services: this.fb.array([]),
      education: this.fb.array([]),
      experience: this.fb.array([]),
    });
  }

  ngOnInit(): void {
    // Charger les données existantes
    //this.profileService.getProfile(1).subscribe((profile) => {
    //  this.profileForm.patchValue(profile);
    //});
  }

  get services(): FormArray {
    return this.profileForm.get('services') as FormArray;
  }

  addService() {
    this.services.push(this.fb.control(''));
  }

  get education(): FormArray {
    return this.profileForm.get('education') as FormArray;
  }

  addEducation() {
    this.education.push(this.fb.group({
      degree: [''],
      college: [''],
      yearOfCompletion: [''],
    }));
  }

  get experience(): FormArray {
    return this.profileForm.get('experience') as FormArray;
  }

  addExperience() {
    this.experience.push(this.fb.group({
      hospitalName: [''],
      designation: [''],
      fromDate: [''],
      toDate: [''],
    }));
  }

  onSubmit() {
    /*this.profileService.saveProfile(this.profileForm.value).subscribe((response) => {
      console.log('Profile saved successfully:', response);
    });*/
  }
}
