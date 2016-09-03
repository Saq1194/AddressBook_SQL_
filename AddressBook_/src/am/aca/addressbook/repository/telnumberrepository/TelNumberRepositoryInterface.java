package am.aca.addressbook.repository.telnumberrepository;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.model.TelNumber;

public interface TelNumberRepositoryInterface {
    TelNumber addTelNumber(TelNumber telNumber)throws InvalidArgumentException;
    void deleteTelNumber(TelNumber telNumber) throws InvalidArgumentException;
    void showTelNumbers()throws InvalidArgumentException;
}
