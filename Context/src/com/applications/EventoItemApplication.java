package com.applications;

import com.domain.Evento;
import com.domain.EventoItem;
import com.domain.Item;
import com.implementations.RepositoryIplement;

public class EventoItemApplication extends RepositoryIplement<EventoItem> {

	public EventoItem Find(Evento evento, Item item) {

		try {
			this.entityManager.getTransaction().begin();
			EventoItem eventoItem = new EventoItem();
			eventoItem.setEvento(evento);
			eventoItem.setItem(item);

			eventoItem = this.entityManager.find(EventoItem.class, eventoItem);

			this.entityManager.getTransaction().commit();

			return eventoItem;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

}
