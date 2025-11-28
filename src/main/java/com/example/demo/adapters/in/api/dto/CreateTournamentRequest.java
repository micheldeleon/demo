
//(lo que llega en el POST para crear un torneo)

package com.example.demo.adapters.in.api.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public record CreateTournamentRequest(
    @NotNull Long disciplineId,
    @NotNull Long formatId,
    @NotBlank String name,
    @NotNull Date startAt,
    @NotNull Date endAt,
    Date registrationDeadline,
    boolean privateTournament,
    String password,
    @Positive int minParticipantsPerTeam,
    @Positive int maxParticipantsPerTeam,
    @PositiveOrZero int minParticipantsPerTournament,
    @PositiveOrZero int maxParticipantsPerTournament,
    String prize,
    @PositiveOrZero double registrationCost
) {}
