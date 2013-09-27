package game.menu;

import java.util.ArrayList;
import java.util.List;

import graphics.GameObject;

public class MainMenu implements Menu {
	
	//lista przycisków, które będą widniały w menu, ręcznie ustawione położenie
	private List<MenuButton> _buttons;
	
	//lista modeli z tłem (tak jak są po kolei, tak beda renderowane, czyli te pozniej dodane beda na wierzchu)
	private List<GameObject> _background;
	
	//zeby nie tworzyc za kazdym razem nowej listy ( w sumie dwa razy to by bylo i tak)
	private List<GameObject> _tempToRender;
	private List<MenuButton> _tempToClick;

	//przycisk nad którym była myszka przy ostatnim update (moze byc null!)
	private MenuButton _mouseLastAtButton;
	
	public MainMenu() {
		_buttons = new ArrayList<MenuButton>();
		_background = new ArrayList<GameObject>();
		_tempToRender = new ArrayList<GameObject>();
		_tempToClick = new ArrayList<MenuButton>();
	}
	
	public MainMenu(List<MenuButton> list) {
		_buttons = list;
		_background = new ArrayList<GameObject>();
		_tempToRender = new ArrayList<GameObject>();
		_tempToClick = new ArrayList<MenuButton>();
	}
	
	public MainMenu(MenuButton[] list) {
		_buttons = new ArrayList<MenuButton>();
		for (int i = 0; i < list.length; i++) {
			_buttons.add(list[i]);
		}
		_background = new ArrayList<GameObject>();
		_tempToRender = new ArrayList<GameObject>();
		_tempToClick = new ArrayList<MenuButton>();
	}
	
	@Override
	public void mouseClick(double x, double y) {
		_tempToClick.clear();
		for (MenuButton but : _buttons) {
			if (but.isMouseWithin(x, y)) {
				_tempToClick.add(but);
			}
		}
		
		if (_tempToClick.size()==0)
			return;
		
		_tempToClick.get(_tempToClick.size()-1).actionOnClick();
		
	}

	@Override
	public List<GameObject> getObjectsToRender() {
		_tempToRender.clear();
		_tempToRender.addAll(_background);
		_tempToRender.addAll(_buttons);
		return _tempToRender;
	}

	@Override
	public void mousePresenceAt(double x, double y) {
		for (MenuButton but : _buttons) {
			if (but.isMouseWithin(x, y)) {
				if (_mouseLastAtButton != but) {
					if (_mouseLastAtButton != null)
					_mouseLastAtButton.actionOnExit();
					but.actionOnEnter();
					_mouseLastAtButton = but;
				}
				return;
			}
		}
		if (_mouseLastAtButton != null) {
			_mouseLastAtButton.actionOnExit();
			_mouseLastAtButton = null;
		}
	}

	@Override
	public void addBackground(GameObject back) {
		_background.add(back);
		
	}

	@Override
	public void addButton(MenuButton but) {
		_buttons.add(but);
		
	}

	@Override
	public void clearBackground() {
		_background.clear();
	}

	@Override
	public void clearButtons() {
		_buttons.clear();
	}
	
	
}
