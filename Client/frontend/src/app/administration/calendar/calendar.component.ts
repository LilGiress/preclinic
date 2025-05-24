import { ChangeDetectorRef, Component, CUSTOM_ELEMENTS_SCHEMA, Inject, PLATFORM_ID, signal } from '@angular/core';
import { CalendarOptions, DateSelectArg, EventApi, EventClickArg } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { FullCalendarModule } from '@fullcalendar/angular';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import { HttpClient } from '@angular/common/http';
import { ModalService } from '../../shared/service/modal.service';



@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [FullCalendarModule],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class CalendarComponent {
  selectedDate: string = ''; // Stocke la date sélectionnée
constructor(
  @Inject(PLATFORM_ID) private platformId: any,
  private http:HttpClient,
  private modalService:ModalService
){}
  calendarOptions: CalendarOptions = {
    plugins: [ interactionPlugin,
      dayGridPlugin,
      timeGridPlugin,
      listPlugin],
    initialView: 'dayGridMonth',
    weekends: true,
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    
    selectMirror: true,
    dayMaxEvents: true,
    editable: true, // Pour permettre le glisser-déposer
    selectable: true, // Pour permettre la sélection des plages horaires
    eventAdd: this.handleEventAdd.bind(this), // Liaison à la méthode
    dateClick: this.handleDateSelect.bind(this), // Gestion de la sélection
    eventChange: this.handleEventChange.bind(this),
    eventRemove: this.handleDateRemove.bind(this)
  };

  handleEventAdd(eventInfo: any): void {
    const event = {
      title: eventInfo.event.title,
      start: eventInfo.event.start,
      end: eventInfo.event.end,
    };
  
    this.http.post('https://api.example.com/events', event).subscribe(
      (response) => {
        console.log('Événement sauvegardé :', response);
      },
      (error) => {
        console.error('Erreur lors de la sauvegarde :', error);
      }
    );
  }

  handleDateSelect(event: any): void {
   /* const title = prompt('Veuillez entrer un titre pour l’événement :');
    const calendarApi = selectInfo.view.calendar;
  
    calendarApi.unselect(); // Désélectionner les dates
  
    if (title) {
      calendarApi.addEvent({
        title,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
        allDay: selectInfo.allDay,
      });
    }*/



      console.log('Date cliquée :', event.dateStr);
      this.selectedDate = event.dateStr; // Stocke la date sélectionnée
      // Ouvrir le modal
      const modal = document.getElementById('add_event');
      if (modal) {
        modal.style.display = 'block'; // Afficher le modal
      }

      

  }


  handleEventChange(changeInfo: any): void {
    const updatedEvent = {
      id: changeInfo.event.id,
      title: changeInfo.event.title,
      start: changeInfo.event.start,
      end: changeInfo.event.end,
      allDay: changeInfo.event.allDay,
    };


    this.http.put(`https://api.example.com/events/${updatedEvent.id}`, updatedEvent).subscribe(
      (response) => {
        console.log('Événement mis à jour dans le backend :', response);
        this.modalService.openSuccessModal("Event")
      },
      (error) => {
        console.error('Erreur lors de la mise à jour :', error);
        this.modalService.openWarning('Une erreur est survenue lors de la création','Attention',5000)
      }
    );

  }


 

  // Liste des événements
  calendarEvents = [
    { title: 'Event Name 1', start: '2025-01-11T02:16:00', color: '#ff0000' },
    { title: 'Test Event 2', start: '2025-01-09T09:09:00', color: '#00ff00' }
  ];

  // Gérer le clic sur une date
  handleDateRemove(arg: any) {
   
  }

  // Gérer le clic sur un événement
  eventClicked(changeInfo: any) {
    alert(`Événement cliqué : ${changeInfo.event.title}`);
    const updatedEvent = {
      id: changeInfo.event.id,
      title: changeInfo.event.title,
      start: changeInfo.event.start,
      end: changeInfo.event.end,
      allDay: changeInfo.event.allDay,
    };

    if (confirm(`Confirmez-vous la modification de l'événement : ${changeInfo.event.title}?`)) {
      // Sauvegarder l'événement

      this.http.put(`https://api.example.com/events/${updatedEvent.id}`, updatedEvent).subscribe(
        (response) => {
          console.log('Événement mis à jour dans le backend :', response);
          this.closeModal()
        },
        (error) => {
          console.error('Erreur lors de la mise à jour :', error);
        }
      );
    } else {
      // Annuler la modification
      changeInfo.revert();
    }
  }


  closeModal(): void {
    const modal = document.getElementById('add_event');
    if (modal) {
      modal.style.display = 'none'; // Fermer le modal
    }
  }
   

  saveEvent(eventData: any): void {
    const event = {
      title: eventData.event.title,
      start: eventData.event.start,
      end: eventData.event.end,
    };
  
    this.http.post('https://api.example.com/events', event).subscribe(
      (response) => {
        console.log('Événement sauvegardé :', response);
      },
      (error) => {
        console.error('Erreur lors de la sauvegarde :', error);
      }
    );
    console.log('Événement sauvegardé pour la date :', this.selectedDate, eventData);
   
    eventData.preventDefault();
    const title = eventData.target.eventTitle.value;
    const description = eventData.target.eventDescription.value;
  
    // Ajouter l'événement au calendrier
    const newEvent = {
      title,
      start: this.selectedDate,
      description,
    };
  
    const calendarApi = (document.querySelector('full-calendar') as any).getApi();
    calendarApi.addEvent(newEvent);
  
    console.log('Événement ajouté :', newEvent);
    this.closeModal();

  }
   

 
 

}
