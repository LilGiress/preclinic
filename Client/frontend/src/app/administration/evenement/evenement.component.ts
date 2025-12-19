import { Component, OnInit } from '@angular/core';
import { IEvent } from '../../models/event';
import { CalendarService } from '../../services/calendar.service';

@Component({
  selector: 'app-evenement',
  imports: [],
  templateUrl: './evenement.component.html',
  styleUrl: './evenement.component.css'
})
export class EvenementComponent implements OnInit {
   userId: number = 0; // Initialiser
  calendarId: number = 0; // Initialiser
  calendarOptions: any = { events: [] }; // Initialiser

  ngOnInit(): void {
     // Charger les événements au démarrage
    if (this.calendarId) {
      this.loadEvents(this.calendarId);
    }
   
  }

  constructor(private calendarService: CalendarService

  ) {}

  handleEventRemove(removeInfo: any) {
  const eventId = removeInfo.event.id;
  this.calendarService.deleteEvent(eventId).subscribe();
}

handleEventChange(changeInfo: any) {
  const updatedEvent: IEvent = {
    id: changeInfo.event.id,
    title: changeInfo.event.title,
    eventDate: changeInfo.event.startStr
  };

  this.calendarService.updateEvent(updatedEvent.id!, updatedEvent).subscribe();
}

handleDateClick(arg: any) {
  const title = prompt("Titre de l'événement :");

  if (!title) return;

  const newEvent: IEvent = {
    title: title,
    eventDate: arg.dateStr,
    relatedUserId: this.userId,
    calendar: { id: this.calendarId }
  };

  this.calendarService.addEvent(newEvent).subscribe({
    next: (created) => {
      arg.view.calendar.addEvent(this.mapToCalendarEvent(created));
    }
  });
}

loadEvents(calendarId: number) {
  this.calendarService.getCalendar(calendarId).subscribe({
    next: (cal) => {

      this.calendarOptions.events =
        cal.events?.map(e => this.mapToCalendarEvent(e));

    }
  });
}

mapToCalendarEvent(event: IEvent) {
  return {
    id: event.id,
    title: event.title,
    start: event.eventDate // FullCalendar accepte ISO string
  };
}



}
