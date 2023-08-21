export * from './games.service';
import {GamesService} from './games.service';
import {UsersService} from './users.service';

export * from './games.serviceInterface';
export * from './users.service';
export * from './users.serviceInterface';
export const APIS = [GamesService, UsersService];
