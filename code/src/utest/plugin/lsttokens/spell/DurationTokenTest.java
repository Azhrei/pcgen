/*
 * Copyright (c) 2007 Tom Parker <thpr@users.sourceforge.net>
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package plugin.lsttokens.spell;

import org.junit.Test;

import pcgen.cdom.enumeration.ListKey;
import pcgen.core.spell.Spell;
import pcgen.persistence.PersistenceLayerException;
import pcgen.rules.persistence.CDOMLoader;
import pcgen.rules.persistence.token.CDOMPrimaryToken;
import plugin.lsttokens.testsupport.AbstractTypeSafeListTestCase;
import plugin.lsttokens.testsupport.CDOMTokenLoader;

public class DurationTokenTest extends AbstractTypeSafeListTestCase<Spell, String>
{

	static DurationToken token = new DurationToken();
	static CDOMTokenLoader<Spell> loader = new CDOMTokenLoader<Spell>();

	@Override
	public Class<Spell> getCDOMClass()
	{
		return Spell.class;
	}

	@Override
	public CDOMLoader<Spell> getLoader()
	{
		return loader;
	}

	@Override
	public CDOMPrimaryToken<Spell> getToken()
	{
		return token;
	}

	@Override
	public String getConstant(String string)
	{
		return string;
	}

	@Override
	public char getJoinCharacter()
	{
		return '|';
	}

	@Override
	public ListKey<String> getListKey()
	{
		return ListKey.DURATION;
	}

	@Override
	public boolean isClearDotLegal()
	{
		return false;
	}

	@Override
	public boolean isClearLegal()
	{
		return true;
	}

	@Override
	protected boolean requiresPreconstruction()
	{
		return false;
	}

	@Test
	public void testGoodParentheses() throws PersistenceLayerException {
		assertTrue(parse("(first)"));
	}
	
	@Test
	public void testBadParentheses() throws PersistenceLayerException {
		assertFalse("Missing end paren should have been flagged.", parse("(first"));
		assertFalse("Missing start paren should have been flagged.", parse("first)"));
		assertFalse("Missing start paren should have been flagged.", parse("(fir)st)"));
		assertFalse("Out of order parens should have been flagged.", parse(")(fir(st)"));
	}

	/*
	 * TODO Need to figure out ownership of this responsibility
	 */
	// @Test
	// public void testUnparseBadParens() throws PersistenceLayerException
	// {
	// primaryProf.addToListFor(getListKey(), "(first");
	// assertBadUnparse();
	//	}

}
