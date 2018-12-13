import { v4 as uuid } from 'uuid';
import { Movie } from './movie/movie';

export const MOVIES: Movie[] = [
  { id: uuid(), title: 'movie1', year: 2001 },
  { id: uuid(), title: 'movie2', year: 2002 },
  { id: uuid(), title: 'movie3', year: 2003 },
  { id: uuid(), title: 'movie4', year: 2004 },
  { id: uuid(), title: 'movie5', year: 2005 },
];
