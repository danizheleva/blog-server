export interface TripList {
  trips: Trip[];
}

export interface Trip {
  id: number;
  tripTitle: string;
  tripSummary: string;
  tripStartDate: Date;

  listOfDays: Day[]

  postCreationDate: Date;
  postEditDate: Date;
}

export interface Day {
  dayNumber: number;
  dayTitle: string;
  dayText: string;
  locations: Locations[];
}

interface Locations {
  city: string;
  country: string;
}

