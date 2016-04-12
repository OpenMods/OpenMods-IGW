package openmods.igw.utils;

public interface IPageInit {
	boolean mustRegister(final String modId);

	void register(final String modId,
				  final Class<? extends igwmod.gui.tabs.IWikiTab> tabClass,
				  final Class<?> eventHandlerClass);

	igwmod.gui.tabs.IWikiTab getTabForModId(final String modId);
}
