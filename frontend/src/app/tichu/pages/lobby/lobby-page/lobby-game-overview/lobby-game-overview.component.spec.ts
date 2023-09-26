import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LobbyGameOverviewComponent} from './lobby-game-overview.component';
import {MatTableModule} from "@angular/material/table";
import {capture, spy} from "ts-mockito";

describe('LobbyGameOverviewComponent', () => {
  let component: LobbyGameOverviewComponent;
  let fixture: ComponentFixture<LobbyGameOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MatTableModule],
      declarations: [LobbyGameOverviewComponent],
    });
    fixture = TestBed.createComponent(LobbyGameOverviewComponent);
    component = fixture.componentInstance;
    component.gamesDataSource = [
      { id: '1' },
      { id: '2' },
    ]
    fixture.detectChanges();
  });

  it('should show a table with an entry for each element in the data source', () => {
    const tableRows: HTMLTableRowElement[] = fixture.nativeElement.querySelectorAll('tr')
    expect(tableRows.length).toBe(3) // first row is the header row
  });

  it('should emit the selected game info when a table row is clicked', () => {
    const selectedGameSpy = spy(component.gameSelected)
    const secondTableRow: HTMLTableRowElement = fixture.nativeElement.querySelectorAll('tr')[1];

    secondTableRow.click()
    fixture.detectChanges()

    const [captured] = capture(selectedGameSpy.emit).first()
    expect(captured?.id).toBe('1')
  })
});
