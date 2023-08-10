export * from './games.service';
import {GamesService} from './games.service';
import {PlayersService} from './players.service';

export * from './games.serviceInterface';
export * from './players.service';
export * from './players.serviceInterface';
export const APIS = [GamesService, PlayersService];
