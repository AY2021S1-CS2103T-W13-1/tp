package com.eva.logic.parser;

import static com.eva.model.comment.CommentCliSyntax.PREFIX_ADD;
import static com.eva.model.comment.CommentCliSyntax.PREFIX_DATE;
import static com.eva.model.comment.CommentCliSyntax.PREFIX_DELETE;
import static com.eva.model.comment.CommentCliSyntax.PREFIX_DESC;
import static com.eva.model.comment.CommentCliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.eva.commons.core.index.Index;
import com.eva.commons.util.StringUtil;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses the commands inside comment input
     * @param comment comment input
     * @return Comment object created with input
     * @throws ParseException
     */
    public static Comment parseComment(String comment) throws ParseException {
        requireNonNull(comment);
        String trimmedComment = comment.trim();
        if (!Comment.isValidComment(trimmedComment)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(" " + comment, PREFIX_ADD,
                PREFIX_DELETE, PREFIX_DATE, PREFIX_TITLE, PREFIX_DESC);
        System.out.println(comment);
        System.out.println(argMultiMap.getAllValues(PREFIX_ADD).size());
        if (argMultiMap.getAllValues(PREFIX_DELETE).size() == 0) {
            String date = argMultiMap.getValue(PREFIX_DATE).get();
            String title = argMultiMap.getValue(PREFIX_TITLE).get();
            String desc = argMultiMap.getValue(PREFIX_DESC).get();
            return new Comment(LocalDate.parse(date), desc, title);
        } else {
            String desc = argMultiMap.getValue(PREFIX_DESC).get();
            return new Comment(desc);
        }
    }

    /**
     * Converts string with details of comments into a Comment object
     * @param comments strings of details
     * @return Set of comment objects
     * @throws ParseException
     */
    public static Set<Comment> parseComments(Collection<String> comments) throws ParseException {
        requireNonNull(comments);
        final Set<Comment> commentSet = new HashSet<>();
        for (String comment : comments) {
            commentSet.add(parseComment(comment));
        }
        return commentSet;
    }

}
