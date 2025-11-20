package com.example.demo.core.application.usecase;


import com.example.demo.core.domain.models.Tournament;
import com.example.demo.core.ports.in.CreateTournamentPort;
import com.example.demo.core.ports.out.SaveTournamentPort;





public class CreateTournamentUseCase implements CreateTournamentPort {

    private final SaveTournamentPort savePort;

    public CreateTournamentUseCase(SaveTournamentPort savePort) {
        this.savePort = savePort;
    }

    @Override
    // Le paso al método crear un torneo para crear, y un id de organizador
    public Tournament create(Tournament t, Long organizerId) {

        // NOTA: Tournament tiene organizer como objeto User (no id).
        // Para no forzar ese mapeo ahora, pasamos organizerId al puerto de
        // persistencia.
        return savePort.save(Tournament.create(t), organizerId);
    }

    // @Override
    // // Le paso al método crear un torneo para crear, y un id de organizador
    // public Tournament create(Tournament t, Long organizerId) {

    // if (t.getName() == null || t.getName().isBlank()) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name es
    // requerido");
    // }

    // // (no pueden ser null). Validamos > 0 y min <= max
    // if (t.getMinParticipantsPerTeam() <= 0 || t.getMaxParticipantsPerTeam() <= 0)
    // {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    // "min/maxParticipantsPerTeam deben ser > 0");
    // }
    // if (t.getMinParticipantsPerTeam() > t.getMaxParticipantsPerTeam()) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    // "minParticipantsPerTeam > maxParticipantsPerTeam");
    // }

    // if (t.isPrivateTournament() && (t.getPassword() == null ||
    // t.getPassword().isBlank())) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password requerido
    // para torneo privado");
    // }

    // // Fechas con java.util.Date -> usar before/after
    // Date start = t.getStartAt();
    // Date end = t.getEndAt();
    // Date deadline = t.getRegistrationDeadline();

    // if (end != null && start != null && end.before(start)) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endAt < startAt");
    // }

    // if (deadline != null && start != null && deadline.after(start)) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    // "registration_deadline > startAt");
    // }

    // // min/max del torneo (son int, 0 si no se usan). Valido sólo si ambos > 0
    // int minT = t.getMinParticipantsPerTournament();
    // int maxT = t.getMaxParticipantsPerTournament();
    // if (minT > 0 && maxT > 0 && minT > maxT) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
    // "minParticipantsPerTournament > maxParticipantsPerTournament");
    // }

    // // -------- Atributos de sistema --------
    // if (t.getCreatedAt() == null) {
    // t.setCreatedAt(new Date());
    // }
    // if (t.getStatus() == null) {
    // t.setStatus(TournamentStatus.ABIERTO); // estado por defecto al crearse
    // }

    // // NOTA: Tournament tiene organizer como objeto User (no id).
    // // Para no forzar ese mapeo ahora, pasamos organizerId al puerto de
    // persistencia.
    // return savePort.save(t, organizerId);
    // }
}
